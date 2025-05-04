import React from 'react';

interface ServiceItemProps {
  id: number;
  carModel: string;
  price: number;
  description: string;
  onDelete: (id: number) => void;
}

const ServiceItem: React.FC<ServiceItemProps> = ({ id, carModel, price, description, onDelete }) => {
  return (
    <div className="service-item">
      <h3>{carModel}</h3>
      <p>Price: ${price}</p>
      <p>Description: {description}</p>
      <button onClick={() => onDelete(id)}>Delete</button>
    </div>
  );
};

export default ServiceItem;