"use client";
import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";

interface ServiceRecord {
  id: number;
  description: string;
}

export default function AddNewReviewPage() {
  const [serviceRecords, setServiceRecords] = useState<ServiceRecord[]>([]);
  const [formData, setFormData] = useState({
    serviceRecordID: "",
    reviewText: "",
    rating: "",
  });
  const router = useRouter();

  // Fetch service records from the backend
  const fetchServiceRecords = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/service-records");
      const data = await response.json();
      setServiceRecords(data);
    } catch (error) {
      console.error("Error fetching service records:", error);
    }
  };

  useEffect(() => {
    fetchServiceRecords();
  }, []);

  const handleChange = (
    e: React.ChangeEvent<
      HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement
    >
  ) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const payload = {
      serviceRecord: {
        id: parseInt(formData.serviceRecordID),
      },
      review: {
        reviewText: formData.reviewText,
        rating: parseInt(formData.rating),
      },
    };

    try {
      const response = await fetch("http://localhost:8080/api/reviews", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });

      if (response.ok) {
        console.log("Review created successfully");
        router.push("/reviews"); // Redirect to the reviews page
      } else {
        console.error("Failed to create review");
      }
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };

  const handleCancel = () => {
    router.push("/reviews");
  };

  return (
    <div className="min-h-screen bg-slate-900 flex items-center justify-center">
      <form
        onSubmit={handleSubmit}
        className="bg-white text-slate-900 w-2/5 p-8 rounded-lg shadow-lg"
      >
        <h1 className="text-2xl font-bold mb-6 text-center">Add New Review</h1>
        <div className="mb-4">
          <label htmlFor="serviceRecordID" className="block font-medium mb-2">
            Service Record:
          </label>
          <select
            id="serviceRecordID"
            name="serviceRecordID"
            value={formData.serviceRecordID}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          >
            <option value="" disabled>
              Select a service
            </option>
            {serviceRecords.map((record) => (
              <option key={record.id} value={record.id}>
                {record.description.length > 30
                  ? `${record.description.slice(0, 30)}...`
                  : record.description}{" "}
                (ID: {record.id})
              </option>
            ))}
          </select>
        </div>
        <div className="mb-4">
          <label htmlFor="reviewText" className="block font-medium mb-2">
            Review Text:
          </label>
          <textarea
            id="reviewText"
            name="reviewText"
            value={formData.reviewText}
            onChange={handleChange}
            required
            className="w-full p-2 border border-gray-300 rounded"
          />
        </div>
        <div className="mb-4">
          <label htmlFor="rating" className="block font-medium mb-2">
            Rating:
          </label>
          <input
            type="number"
            id="rating"
            name="rating"
            value={formData.rating}
            onChange={handleChange}
            required
            min="1"
            max="5"
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
