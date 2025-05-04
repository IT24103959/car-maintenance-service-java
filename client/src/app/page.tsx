"use client";

import { useEffect, useState } from "react";
import HomePage from "./components/HomePage";

export default function Home() {
  const [services, setServices] = useState([]);

  const fetchServices = async () => {
    const response = await fetch("http://localhost:8080/api/service-records");
    const data = await response.json();
    setServices(data);
  };

  useEffect(() => {
    fetchServices();
  }, []);

  return (
    <div className="h-screen w-screen">
      <HomePage />
    </div>
  );
}
