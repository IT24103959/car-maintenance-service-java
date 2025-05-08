"use client";
import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { AiOutlineHome } from "react-icons/ai";

interface Admin {
  id: number;
  name: string;
  email: string;
}

export default function AdminPage() {
  const [admins, setAdmins] = useState<Admin[]>([]);
  const router = useRouter();

  // Fetch admins from the backend
  const fetchAdmins = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/admins");
      const data = await response.json();
      setAdmins(data);
    } catch (error) {
      console.error("Error fetching admins:", error);
    }
  };

  useEffect(() => {
    fetchAdmins();
  }, []);

  const handleHome = () => {
    router.push("/");
  };

  const handleCreateAdmin = async () => {
    router.push("/admin/addNewAdmin");
  };

  const handleDeleteAdmin = async (id: number) => {
    try {
      const response = await fetch(`http://localhost:8080/api/admins/${id}`, {
        method: "DELETE",
      });
      if (response.ok) {
        fetchAdmins(); // Refresh the admins list
      }
    } catch (error) {
      console.error("Error deleting admin:", error);
    }
  };

  return (
    <div className="min-h-screen bg-slate-900 flex flex-col items-center py-8 pt-20">
      <div className="w-4/5 flex justify-between mb-4">
        <div className="flex space-x-2">
          {/* Home Button */}
          <button
            onClick={handleHome}
            className="bg-gray-100 hover:bg-gray-200 text-slate-900 font-bold py-2 text-xl px-4 rounded flex items-center"
          >
            <AiOutlineHome />
          </button>

          <button
            onClick={handleCreateAdmin}
            className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
          >
            Create Admin
          </button>
        </div>
      </div>

      {/* Admins Table */}
      <h2 className="text-xl font-semibold text-white mb-4">Current Admins</h2>
      <table className="w-4/5 bg-white text-slate-900 rounded-lg shadow-lg text-left mb-8">
        <thead>
          <tr className="bg-gray-200">
            <th className="py-2 px-4">ID</th>
            <th className="py-2 px-4">Name</th>
            <th className="py-2 px-4">Email</th>
            <th className="py-2 px-4">Actions</th>
          </tr>
        </thead>
        <tbody>
          {admins.map((admin) => (
            <tr key={admin.id} className="border-b">
              <td className="py-2 px-4">{admin.id}</td>
              <td className="py-2 px-4">{admin.name}</td>
              <td className="py-2 px-4">{admin.email}</td>
              <td className="py-2 px-4">
                <button
                  onClick={() => handleDeleteAdmin(admin.id)}
                  className="bg-red-500 text-white py-1 px-3 rounded hover:bg-red-600"
                >
                  Delete
                </button>
                <button
                  onClick={() => router.push(`/admin/editAdmin/${admin.id}`)}
                  className="bg-yellow-500 text-white py-1 px-3 rounded hover:bg-yellow-600 ml-2"
                >
                  Edit
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
