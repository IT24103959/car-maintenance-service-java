"use client";
import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { AiOutlineHome } from "react-icons/ai"; // Import the home icon

interface Owner {
  name: string;
  tel: string;
  email: string;
  address: string;
}

interface Car {
  carType: string;
  manufacturer: string;
  modelType: string;
  vin: string;
  registrationNumber: string;
  owner: Owner;
}

interface ServiceRecord {
  id: number;
  date: string;
  description: string;
  cost: number;
  car: Car;
  completed?: boolean;
}

export default function Page() {
  const [services, setServices] = useState<ServiceRecord[]>([]);
  const [sortOrder, setSortOrder] = useState("asc");
  const router = useRouter();

  // Fetch services from the backend
  const fetchServices = async (order = "asc") => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/service-records?order=${order}`
      );
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
    fetchServices(newOrder);
  };

  // Handle delete service
  const handleDelete = async (id: number) => {
    try {
      await fetch(`http://localhost:8080/api/service-records/${id}`, {
        method: "DELETE",
      });
      fetchServices(sortOrder); // Refresh the table after deletion
    } catch (error) {
      console.error("Error deleting service:", error);
    }
  };

  const handleHome = () => {
    router.push("/");
  };

  // Handle mark as complete
  const handleComplete = async (id: number) => {
    try {
      await fetch(`http://localhost:8080/api/service-records/complete/${id}`, {
        method: "PATCH",
      });
      fetchServices(sortOrder); // Refresh the table after marking as complete
    } catch (error) {
      console.error("Error marking service as complete:", error);
    }
  };

  // Redirect to the add new service page
  const handleAddNew = () => {
    router.push("/services/addNewService");
  };

  // Redirect to the edit service page
  const handleEdit = (id: number) => {
    router.push(`/services/editService/${id}`);
  };

  return (
    <div className="min-h-screen bg-slate-900 flex flex-col items-center pt-20 pb-8">
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
            onClick={handleAddNew}
            className="bg-red-500 text-white py-2 px-4 rounded hover:bg-red-700"
          >
            Add New
          </button>
        </div>
      </div>
      <table className="w-4/5 bg-white text-slate-900 rounded-lg shadow-lg text-left">
        <thead>
          <tr className="bg-gray-200">
            <th className="py-2 px-4">ID</th>
            <th
              className="py-2 px-4 cursor-pointer flex items-center justify-center"
              onClick={handleSort}
            >
              Date
              <span className="ml-2">{sortOrder === "asc" ? "▲" : "▼"}</span>
            </th>
            <th className="py-2 px-4">Car Info</th>
            <th className="py-2 px-4">Owner Name</th>
            <th className="py-2 px-4">Contact</th>
            <th className="py-2 px-4">Status</th>
            <th className="py-2 px-4">Actions</th>
          </tr>
        </thead>
        <tbody>
          {services.map((service) => (
            <tr key={service.id} className="border-b">
              <td className="py-2 px-4">{service.id}</td>
              <td className="py-2 px-4">
                {new Date(service.date).toLocaleDateString()}
              </td>
              <td className="py-2 px-4">
                {service.car.manufacturer} {service.car.modelType}
              </td>
              <td className="py-2 px-4">{service.car.owner.name}</td>
              <td className="py-2 px-4">{service.car.owner.tel}</td>
              <td className="py-2 px-4">
                {service.completed ? (
                  <span
                    className="inline-block w-4 h-4 rounded-full bg-green-500"
                    title="Completed"
                  ></span>
                ) : (
                  <span
                    className="inline-block w-4 h-4 rounded-full bg-red-500"
                    title="Not Completed"
                  ></span>
                )}
              </td>
              <td className="py-2 px-4 flex space-x-2">
                <button
                  onClick={() => handleComplete(service.id)}
                  disabled={service.completed}
                  className={`py-1 px-3 rounded text-white ${
                    service.completed
                      ? "bg-gray-400 cursor-not-allowed"
                      : "bg-green-500 hover:bg-green-600"
                  }`}
                >
                  Complete
                </button>
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
