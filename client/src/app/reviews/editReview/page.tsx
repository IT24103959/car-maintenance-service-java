"use client";
import React, { useState, useEffect } from "react";
import { useRouter, useSearchParams } from "next/navigation";

export default function EditReviewPage() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const serviceId = searchParams.get("serviceId");

  const [formData, setFormData] = useState({
    reviewText: "",
    rating: 1,
  });

  useEffect(() => {
    if (serviceId) {
      fetch(`http://localhost:8080/api/reviews/${serviceId}`)
        .then((response) => response.json())
        .then((data) => {
          setFormData({
            reviewText: data.reviewText,
            rating: data.rating,
          });
        })
        .catch((error) => console.error("Error fetching review:", error));
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
    try {
      const response = await fetch(
        `http://localhost:8080/api/reviews/${serviceId}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ ...formData, serviceId }),
        }
      );
      if (response.ok) {
        router.push("/reviews");
      } else {
        alert("Failed to update review");
      }
    } catch (error) {
      console.error("Error updating review:", error);
    }
  };

  const handleCancel = () => {
    router.push("/reviews");
  };

  return (
    <div className="min-h-screen bg-slate-900 flex items-center justify-center">
      <form
        onSubmit={handleSubmit}
        className="bg-white text-slate-900 w-96 p-8 rounded-lg shadow-lg"
      >
        <h1 className="text-2xl font-bold mb-6 text-center">Edit Review</h1>
        <div className="mb-4">
          <label htmlFor="reviewText" className="block font-medium mb-2">
            Review:
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
            min={1}
            max={5}
            value={formData.rating}
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
