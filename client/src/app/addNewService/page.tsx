"use client";
import React, { useState } from "react";

export default function Page() {
  const [formData, setFormData] = useState({
    carModel: "",
    carNumberPlate: "",
    servicePrice: "",
    serviceDate: "",
    clientName: "",
    clientContact: "",
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Replace with your POST request logic
    console.log("Form submitted:", formData);
  };

  return (
    <div className="min-h-screen bg-slate-900 flex items-center justify-center">
      <form
        onSubmit={handleSubmit}
        className="bg-white text-slate-900 w-1/4 p-8 rounded-lg shadow-lg"
      >
        <h1 className="text-2xl font-bold mb-6 text-center">New Service</h1>
        <div className="mb-4">
          <label htmlFor="carModel" className="block font-medium mb-2">
            Car Model:
          </label>
          <input
            type="text"
            id="carModel"
            name="carModel"
            value={formData.carModel}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="carNumberPlate" className="block font-medium mb-2">
            Car Number Plate:
          </label>
          <input
            type="text"
            id="carNumberPlate"
            name="carNumberPlate"
            value={formData.carNumberPlate}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="servicePrice" className="block font-medium mb-2">
            Service Price:
          </label>
          <input
            type="number"
            id="servicePrice"
            name="servicePrice"
            value={formData.servicePrice}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="serviceDate" className="block font-medium mb-2">
            Service Date:
          </label>
          <input
            type="date"
            id="serviceDate"
            name="serviceDate"
            value={formData.serviceDate}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="clientName" className="block font-medium mb-2">
            Client Name:
          </label>
          <input
            type="text"
            id="clientName"
            name="clientName"
            value={formData.clientName}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="clientContact" className="block font-medium mb-2">
            Client Contact:
          </label>
          <input
            type="text"
            id="clientContact"
            name="clientContact"
            value={formData.clientContact}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <button className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
          Submit
        </button>
      </form>
    </div>
  );
}
