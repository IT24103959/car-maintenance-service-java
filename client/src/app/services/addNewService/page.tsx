"use client";
import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";

interface Car {
  manufacturer: string;
  modelType: string;
  vin: string | null;
  registrationNumber: string | null;
  owner: object | null;
  carType: string;
}

export default function Page() {
  const [cars, setCars] = useState<Car[]>([]);
  const [formData, setFormData] = useState({
    selectedCar: "",
    servicePrice: "",
    serviceDate: "",
    ownerName: "",
    ownerContact: "",
    jobDescription: "",
    ownerEmail: "",
    ownerAddress: "",
  });
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

  const handleChange = (
    e: React.ChangeEvent<
      HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement
    >
  ) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    // Find the selected car
    const selectedCar = cars.find(
      (car) => `${car.manufacturer} ${car.modelType}` === formData.selectedCar
    );

    if (!selectedCar) {
      console.error("Selected car not found");
      return;
    }

    const payload = {
      service: {
        date: formData.serviceDate,
        description: formData.jobDescription,
        cost: parseFloat(formData.servicePrice),
      },
      car: {
        manufacturer: selectedCar.manufacturer,
        modelType: selectedCar.modelType,
        vin: selectedCar.vin,
        registrationNumber: selectedCar.registrationNumber,
        carType: selectedCar.carType,
      },
      owner: {
        name: formData.ownerName,
        tel: formData.ownerContact,
        email: formData.ownerEmail,
        address: formData.ownerAddress,
      },
    };

    try {
      const response = await fetch(
        "http://localhost:8080/api/service-records",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(payload),
        }
      );

      if (response.ok) {
        console.log("Service record added successfully");
        router.push("/services");
      } else {
        console.error("Failed to add service record");
      }
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };

  const handleCancel = () => {
    router.push("/");
  };

  return (
    <div className="min-h-screen bg-slate-900 flex items-center justify-center">
      <form
        onSubmit={handleSubmit}
        className="bg-white text-slate-900 w-3/5 p-8 rounded-lg shadow-lg"
      >
        <h1 className="text-2xl font-bold mb-6 text-center">New Service</h1>
        <div className="grid grid-cols-2 gap-6">
          {/* Left Side */}
          <div>
            <div className="mb-4">
              <label htmlFor="selectedCar" className="block font-medium mb-2">
                Car:
              </label>
              <select
                id="selectedCar"
                name="selectedCar"
                value={formData.selectedCar}
                onChange={handleChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              >
                <option value="" disabled>
                  Select a car
                </option>
                {cars.map((car, index) => (
                  <option
                    key={index}
                    value={`${car.manufacturer} ${car.modelType}`}
                  >
                    {car.manufacturer} {car.modelType} ({car.carType})
                  </option>
                ))}
              </select>
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
                type="datetime-local"
                id="serviceDate"
                name="serviceDate"
                value={formData.serviceDate}
                onChange={handleChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              />
            </div>
          </div>

          {/* Right Side */}
          <div>
            <div className="mb-4">
              <label htmlFor="ownerName" className="block font-medium mb-2">
                Owner Name:
              </label>
              <input
                type="text"
                id="ownerName"
                name="ownerName"
                value={formData.ownerName}
                onChange={handleChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              />
            </div>
            <div className="mb-4">
              <label htmlFor="ownerContact" className="block font-medium mb-2">
                Owner Contact:
              </label>
              <input
                type="text"
                id="ownerContact"
                name="ownerContact"
                value={formData.ownerContact}
                onChange={handleChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              />
            </div>
            <div className="mb-4">
              <label htmlFor="ownerEmail" className="block font-medium mb-2">
                Owner Email:
              </label>
              <input
                type="email"
                id="ownerEmail"
                name="ownerEmail"
                value={formData.ownerEmail}
                onChange={handleChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              />
            </div>
            <div className="mb-4">
              <label htmlFor="ownerAddress" className="block font-medium mb-2">
                Owner Address:
              </label>
              <input
                type="text"
                id="ownerAddress"
                name="ownerAddress"
                value={formData.ownerAddress}
                onChange={handleChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              />
            </div>
          </div>
        </div>

        {/* Full Width Fields */}
        <div className="mb-4">
          <label htmlFor="jobDescription" className="block font-medium mb-2">
            Job Description:
          </label>
          <textarea
            id="jobDescription"
            name="jobDescription"
            value={formData.jobDescription}
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
