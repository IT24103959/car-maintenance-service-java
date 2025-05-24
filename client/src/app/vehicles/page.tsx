"use client";
import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";

interface Car {
  id: number;
  carType: string;
  manufacturer: string;
  modelType: string;
  owner: null | object;
}

export default function CarsPage() {
  const [cars, setCars] = useState<Car[]>([]);
  const router = useRouter();

  // Fetch cars from the backend
  const fetchCars = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/cars");
      const data = await response.json();
      setCars(data);
    } catch (error) {
      console.error("Error fetching cars:", error);
    }
  };

  useEffect(() => {
    fetchCars();
  }, []);

  const handleCreateCar = () => {
    router.push("/vehicles/addNewCar");
  };

  // Delete car by index (assuming backend returns cars in same order)
  const handleDeleteCar = async (id: number) => {
    try {
      const response = await fetch(`http://localhost:8080/api/cars/${id}`, {
        method: "DELETE",
      });
      if (response.ok) {
        fetchCars(); // Refresh the cars list
      }
    } catch (error) {
      console.error("Error deleting car:", error);
    }
  };

  return (
    <div className="min-h-screen bg-slate-900 flex flex-col items-center py-8 pt-20">
      <div className="w-4/5 flex justify-between mb-4">
        <h1 className="text-2xl font-bold text-white">Cars</h1>
        <button
          onClick={handleCreateCar}
          className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
        >
          Create Car
        </button>
      </div>

      {/* Cars Table */}
      <table className="w-4/5 bg-white text-slate-900 rounded-lg shadow-lg text-left">
        <thead>
          <tr className="bg-gray-200">
            <th className="py-2 px-4">Car Type</th>
            <th className="py-2 px-4">Manufacturer</th>
            <th className="py-2 px-4">Model</th>
            <th className="py-2 px-4">Actions</th>
          </tr>
        </thead>
        <tbody>
          {cars.map((car, _) => (
            <tr key={car.id} className="border-b">
              <td className="py-2 px-4">{car.carType}</td>
              <td className="py-2 px-4">{car.manufacturer}</td>
              <td className="py-2 px-4">{car.modelType}</td>
              <td className="py-2 px-4">
                <button
                  onClick={() => handleDeleteCar(car.id)}
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
