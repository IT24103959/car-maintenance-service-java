"use client";
import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { AiFillStar, AiOutlineStar } from "react-icons/ai"; // Import filled and outlined star icons
import { MdOutlineDirectionsCar, MdDateRange } from "react-icons/md"; // Import car and date icons
import { HiOutlineMail } from "react-icons/hi"; // Import email icon
import { BsPerson } from "react-icons/bs"; // Import person icon

interface Review {
  reviewText: string;
  rating: number;
  serviceRecordID: number;
  serviceDate: string;
  carDetails: string;
  ownerName: string;
  ownerEmail: string;
}

export default function ReviewsPage() {
  const [reviews, setReviews] = useState<Review[]>([]);
  const router = useRouter();

  // Fetch reviews from the backend
  const fetchReviews = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/reviews");
      const data = await response.json();

      // Map the response to match the Review interface
      const mappedReviews = data.map((review: any) => ({
        reviewText: review.reviewText,
        rating: review.rating,
        serviceDate: review.serviceRecord?.date || "N/A",
        carDetails: `${review.serviceRecord?.car?.manufacturer || "Unknown"} ${
          review.serviceRecord?.car?.modelType || "Unknown"
        } (${review.serviceRecord?.car?.carType || "Unknown"})`,
        ownerName: review.serviceRecord?.car?.owner?.name || "Unknown",
        ownerEmail: review.serviceRecord?.car?.owner?.email || "Unknown",
      }));

      setReviews(mappedReviews);
    } catch (error) {
      console.error("Error fetching reviews:", error);
    }
  };

  useEffect(() => {
    fetchReviews();
  }, []);

  const handleCreateReview = () => {
    router.push("/reviews/addNewReview");
  };

  return (
    <div className="min-h-screen bg-slate-900 flex flex-col items-center py-8 pt-20">
      <div className="w-4/5 flex justify-between mb-8">
        <h1 className="text-2xl font-bold text-white">Reviews</h1>
        <button
          onClick={handleCreateReview}
          className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
        >
          Create Review
        </button>
      </div>

      {/* Reviews Grid */}
      <div className="w-4/5 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {reviews.map((review, index) => (
          <div
            key={index}
            className="bg-transparent border border-white text-white p-6 rounded-lg shadow-lg hover:scale-105 hover:shadow-xl transition-transform duration-300"
          >
            {/* Ratings */}
            <div className="flex items-center mb-4">
              {Array.from({ length: 5 }, (_, i) =>
                i < review.rating ? (
                  <AiFillStar
                    key={i}
                    className="text-yellow-500 text-3xl mr-1"
                  />
                ) : (
                  <AiOutlineStar
                    key={i}
                    className="text-gray-500 text-3xl mr-1"
                  />
                )
              )}
            </div>

            {/* Review Text */}
            <p className="text-white text-lg mb-4 italic font-bold">
              "{review.reviewText}"
            </p>

            {/* Car Details */}
            <p className="text-gray-300 text-sm mb-2 flex items-center">
              <MdOutlineDirectionsCar className="text-green-500 text-2xl mr-2" />
              {review.carDetails}
            </p>

            {/* Owner Email */}
            <p className="text-gray-300 text-sm mb-2 flex items-center">
              <HiOutlineMail className="text-yellow-500 text-2xl mr-2" />
              {review.ownerEmail}
            </p>

            {/* Service Date */}
            <p className="text-gray-300 text-sm mb-2 flex items-center">
              <MdDateRange className="text-red-500 text-2xl mr-2" />
              <span className="font-semibold"></span> {review.serviceDate}
            </p>

            {/* Owner Name */}
            <p className="text-gray-300 text-sm mb-4 flex items-center">
              <BsPerson className="text-cyan-500 text-2xl mr-2" />
              <span className="font-semibold"></span> {review.ownerName}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}
