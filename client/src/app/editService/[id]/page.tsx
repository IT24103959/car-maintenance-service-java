"use client";
import React, { useState, useEffect, use } from "react";
import { useRouter } from "next/navigation";

export default function EditServicePage({
  params,
}: {
  params: Promise<{ id: string }>;
}) {
  const [formData, setFormData] = useState({
    carModel: "",
    carNumberPlate: "",
    servicePrice: "",
    serviceDate: "",
    ownerName: "",
    ownerContact: "",
    jobDescription: "",
    carManufacturer: "",
    carType: "",
    ownerEmail: "",
    ownerAddress: "",
    vin: "",
  });

  const router = useRouter();
  const { id: serviceId } = use(params);

  useEffect(() => {
    if (serviceId) {
      fetch(`http://localhost:8080/api/service-records/${serviceId}`)
        .then((response) => response.json())
        .then((data) => {
          setFormData({
            carModel: data.car.modelType,
            carNumberPlate: data.car.registrationNumber,
            servicePrice: data.cost.toString(),
            serviceDate: data.date,
            ownerName: data.car.owner.name,
            ownerContact: data.car.owner.tel,
            jobDescription: data.description,
            carManufacturer: data.car.manufacturer,
            carType: data.car.carType,
            ownerEmail: data.car.owner.email,
            ownerAddress: data.car.owner.address,
            vin: data.car.vin,
          });
        })
        .catch((error) =>
          console.error("Error fetching service record:", error)
        );
    }
  }, [serviceId]);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const payload = {
      service: {
        date: formData.serviceDate,
        description: formData.jobDescription,
        cost: parseFloat(formData.servicePrice), // Ensure cost is a double
      },
      car: {
        manufacturer: formData.carManufacturer,
        modelType: formData.carModel,
        vin: formData.vin,
        registrationNumber: formData.carNumberPlate,
        carType: formData.carType,
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
        `http://localhost:8080/api/service-records/${serviceId}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(payload),
        }
      );

      if (response.ok) {
        console.log("Service record updated successfully");
        router.push("/serviceHistory");
      } else {
        console.error("Failed to update service record");
      }
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };

  const handleCancel = () => {
    router.push("/serviceHistory");
  };

  return (
    <div className="min-h-screen bg-slate-900 flex items-center justify-center">
      <form
        onSubmit={handleSubmit}
        className="bg-white text-slate-900 w-3/5 p-8 rounded-lg shadow-lg"
      >
        <h1 className="text-2xl font-bold mb-6 text-center">Edit Service</h1>
        <div className="grid grid-cols-2 gap-6">
          {/* Left Side */}
          <div>
            <div className="mb-4">
              <label
                htmlFor="carManufacturer"
                className="block font-medium mb-2"
              >
                Car Manufacturer:
              </label>
              <input
                type="text"
                id="carManufacturer"
                name="carManufacturer"
                value={formData.carManufacturer}
                onChange={handleChange}
                required
                className="w-full p-2 border border-gray-300 rounded"
              />
            </div>
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
              <label
                htmlFor="carNumberPlate"
                className="block font-medium mb-2"
              >
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
          </div>

          {/* Right Side */}
          <div>
            <div className="mb-4">
              <label htmlFor="servicePrice" className="block font-medium mb-2">
                Service Price:
              </label>
              <input
                type="number"
                step="0.01"
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
          </div>
        </div>

        {/* Full Width Fields */}
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
