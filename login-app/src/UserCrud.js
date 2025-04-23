import React, { useState, useEffect } from "react";
import axios from "axios";

const API_BASE = "http://localhost:8080/api/users";

function UserCrud() {

  const [token, setToken] = useState("");

  useEffect(() => {
    const storedToken = localStorage.getItem('token');
    if (storedToken) {
      setToken(storedToken);
    }
  }, []);


  const [view, setView] = useState(""); // list, create, update, delete
  const [users, setUsers] = useState([]);
  const [form, setForm] = useState({ username: "", password: "" });
  const [message, setMessage] = useState("");

  // Handlers
  const handleInputChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const listUsers = async () => {
    try {
      const res = await axios.get(API_BASE, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      setUsers(res.data);
      setMessage("");
    } catch (err) {
      setMessage("Failed to fetch users");
    }
  };

  const createUser = async (e) => {
    e.preventDefault();
    try {
      await axios.post(API_BASE + '/createUser', form, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      setMessage("User created");
    } catch {
      setMessage("Failed to create user");
    }
  };

  const updateUser = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`${API_BASE}`, form, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      setMessage("User updated");
    } catch {
      setMessage("Failed to update user");
    }
  };

  const deleteUser = async (e) => {
    e.preventDefault();
    try {
      await axios.delete(`${API_BASE}/${form.username}`, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      setMessage("User deleted");
    } catch {
      setMessage("Failed to delete user");
    }
  };

  // Render logic
  return (
    <div>
      <button onClick={() => { setView("list"); listUsers(); }}>List User</button>
      <button onClick={() => { setView("create"); setForm({ username: "", password: "" }); }}>Create User</button>
      <button onClick={() => { setView("update"); setForm({ username: "", password: "" }); }}>Update User</button>
      <button onClick={() => { setView("delete"); setForm({ username: "" }); }}>Delete User</button>

      {view === "list" && (
        <div>
          <h3>User List</h3>
          <ul>
            {users.map((v, k) => <li key={k}>{v}</li>)}
          </ul>
        </div>
      )}

      {view === "create" && (
        <form onSubmit={createUser}>
          <input name="username" placeholder="Username" value={form.username} onChange={handleInputChange} required />
          <input name="password" type="password" placeholder="Password" value={form.password} onChange={handleInputChange} required />
          <button type="submit">Submit</button>
        </form>
      )}

      {view === "update" && (
        <form onSubmit={updateUser}>
          <input name="username" placeholder="Username" value={form.username} onChange={handleInputChange} required />
          <input name="password" type="password" placeholder="New Password" value={form.password} onChange={handleInputChange} required />
          <button type="submit">Submit</button>
        </form>
      )}

      {view === "delete" && (
        <form onSubmit={deleteUser}>
          <input name="username" placeholder="Username" value={form.username} onChange={handleInputChange} required />
          <button type="submit">Delete</button>
        </form>
      )}

      {message && <p>{message}</p>}
    </div>
  );
}

export default UserCrud;
