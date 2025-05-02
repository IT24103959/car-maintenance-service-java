"use client";
import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";

export default function Page() {
  const [services, setServices] = useState([]);
  const [sortOrder, setSortOrder] = useState("asc");
  const router = useRouter();

  // Fetch services from the backend
  const fetchServices = async (order = "asc") => {
    try {
      const response = await fetch(`/api/services?order=${order}`);
      const data = await response.json();
      setServices(data);
    } catch (error) {
      console.error("Error fetching services:", error);
    }
  };

  useEffect(() => {
    fetchServices(sortOrder);
  }, [sortOrder]);

  // Handle sorting by date
  const handleSort = () => {
    const newOrder = sortOrder === "asc" ? "desc" : "asc";
    setSortOrder(newOrder);
  };

  // Handle delete service
  const handleDelete = async (id) => {
    try {
      await fetch(`/api/services/${id}`, { method: "DELETE" });
      fetchServices(sortOrder); // Refresh the table after deletion
    } catch (error) {
      console.error("Error deleting service:", error);
    }
  };

  // Redirect to the add new service page
  const handleAddNew = () => {
    router.push("/addNewService");
  };

  // Redirect to the edit service page
  const handleEdit = (id) => {
    router.push(`/editService/${id}`);
  };

  return (
    <div className="min-h-screen bg-slate-900 flex flex-col items-center py-8">
      <div className="w-4/5 flex justify-between mb-4">
        <button
          onClick={handleAddNew}
          className="bg-red-500 text-white py-2 px-4 rounded hover:bg-red-700"
        >
          Add New
        </button>
      </div>
      <table className="w-4/5 bg-white text-slate-900 rounded-lg shadow-lg">
        <thead>
          <tr className="bg-gray-200">
            <th className="py-2 px-4">Car Model</th>
            <th className="py-2 px-4">Car Number Plate</th>
            <th className="py-2 px-4">Service Price</th>
            <th className="py-2 px-4">Client Name</th>
            <th className="py-2 px-4">Client Contact</th>
            <th className="py-2 px-4">Actions</th>
            <th
              className="py-2 px-4 cursor-pointer flex items-center justify-center"
              onClick={handleSort}
            >
              Service Date
              <span className="ml-2">{sortOrder === "asc" ? "▲" : "▼"}</span>
            </th>
          </tr>
        </thead>
        <tbody>
          {services.map((service) => (
            <tr key={service.id} className="border-b">
              <td className="py-2 px-4">{service.carModel}</td>
              <td className="py-2 px-4">{service.carNumberPlate}</td>
              <td className="py-2 px-4">{service.servicePrice}</td>
              <td className="py-2 px-4">{service.clientName}</td>
              <td className="py-2 px-4">{service.clientContact}</td>
              <td className="py-2 px-4">{service.serviceDate}</td>
              <td className="py-2 px-4 flex space-x-2">
                <button
                  onClick={() => handleEdit(service.id)}
                  className="bg-yellow-500 text-white py-1 px-3 rounded hover:bg-yellow-600"
                >
                  Edit
                </button>
                <button
                  onClick={() => handleDelete(service.id)}
                  className="bg-red-500 text-white py-1 px-3 rounded hover:bg-red-600"
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
