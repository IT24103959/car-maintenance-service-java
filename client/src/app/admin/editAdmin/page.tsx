"use client";
import React, { useState, useEffect } from "react";
import { useRouter, useSearchParams } from "next/navigation";

export default function EditAdminPage() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const adminId = searchParams.get("id");

  const [formData, setFormData] = useState({
    name: "",
    email: "",
  });

  useEffect(() => {
    if (adminId) {
      fetch(`http://localhost:8080/api/admins/${adminId}`)
        .then((response) => response.json())
        .then((data) => {
          setFormData({
            name: data.name,
            email: data.email,
          });
        })
        .catch((error) => console.error("Error fetching admin:", error));
    }
  }, [adminId]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await fetch(
        `http://localhost:8080/api/admins/${adminId}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(formData),
        }
      );
      if (response.ok) {
        router.push("/admin");
      } else {
        alert("Failed to update admin");
      }
    } catch (error) {
      console.error("Error updating admin:", error);
    }
  };

  const handleCancel = () => {
    router.push("/admin");
  };

  return (
    <div className="min-h-screen bg-slate-900 flex items-center justify-center">
      <form
        onSubmit={handleSubmit}
        className="bg-white text-slate-900 w-96 p-8 rounded-lg shadow-lg"
      >
        <h1 className="text-2xl font-bold mb-6 text-center">Edit Admin</h1>
        <div className="mb-4">
          <label htmlFor="name" className="block font-medium mb-2">
            Name:
          </label>
          <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="email" className="block font-medium mb-2">
            Email:
          </label>
          <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="flex justify-end space-x-4">
          <button
            type="submit"
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
          >
            Update
          </button>
          <button
            type="button"
            onClick={handleCancel}
            className="bg-gray-100 hover:bg-gray-200 text-slate-900 font-bold py-2 px-4 rounded"
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
}
