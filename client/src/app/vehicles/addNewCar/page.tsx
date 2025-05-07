"use client";
import React, { useState } from "react";
import { useRouter } from "next/navigation";

export default function AddNewCarPage() {
  const [formData, setFormData] = useState({
    carType: "",
    manufacturer: "",
    modelType: "",
    vin: "",
    registrationNumber: "",
  });
  const router = useRouter();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const payload = {
      carType: formData.carType,
      manufacturer: formData.manufacturer,
      modelType: formData.modelType,
      vin: formData.vin,
      registrationNumber: formData.registrationNumber,
    };

    try {
      const response = await fetch("http://localhost:8080/api/cars", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });

      if (response.ok) {
        console.log("Car created successfully");
        router.push("/vehicle"); // Redirect to the cars management page
      } else {
        console.error("Failed to create car");
      }
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };

  const handleCancel = () => {
    router.push("/vehicles");
  };

  return (
    <div className="min-h-screen bg-slate-900 flex items-center justify-center">
      <form
        onSubmit={handleSubmit}
        className="bg-white text-slate-900 w-2/5 p-8 rounded-lg shadow-lg"
      >
        <h1 className="text-2xl font-bold mb-6 text-center">Add New Car</h1>
        <div className="mb-4">
          <label htmlFor="carType" className="block font-medium mb-2">
            Car Type:
          </label>
          <input
            type="text"
            id="carType"
            name="carType"
            value={formData.carType}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="manufacturer" className="block font-medium mb-2">
            Manufacturer:
          </label>
          <input
            type="text"
            id="manufacturer"
            name="manufacturer"
            value={formData.manufacturer}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="modelType" className="block font-medium mb-2">
            Model Type:
          </label>
          <input
            type="text"
            id="modelType"
            name="modelType"
            value={formData.modelType}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="vin" className="block font-medium mb-2">
            VIN:
          </label>
          <input
            type="text"
            id="vin"
            name="vin"
            value={formData.vin}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label
            htmlFor="registrationNumber"
            className="block font-medium mb-2"
          >
            Registration Number:
          </label>
          <input
            type="text"
            id="registrationNumber"
            name="registrationNumber"
            value={formData.registrationNumber}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="flex justify-end space-x-4">
          <button
            type="submit"
            className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
          >
            Submit
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
