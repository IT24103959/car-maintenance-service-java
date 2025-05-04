import { useEffect, useState } from "react";
import ServiceItem from "./ServiceItem";

const ServiceList = () => {
  const [services, setServices] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchServices = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/service-records");
        if (!response.ok) {
          throw new Error("Failed to fetch services");
        }
        const data = await response.json();
        setServices(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchServices();
  }, []);

  const handleSortByDate = () => {
    const sortedServices = [...services].sort((a, b) => new Date(b.date) - new Date(a.date));
    setServices(sortedServices);
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <button onClick={handleSortByDate}>Sort by Date</button>
      <ul>
        {services.map(service => (
          <ServiceItem key={service.id} service={service} />
        ))}
      </ul>
    </div>
  );
};

export default ServiceList;