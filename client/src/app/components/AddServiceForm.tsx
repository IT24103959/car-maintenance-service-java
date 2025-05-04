import { useState } from "react";

const AddServiceForm = () => {
  const [carModel, setCarModel] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const serviceData = {
      carModel,
      price,
      description,
    };

    try {
      const response = await fetch("http://localhost:8080/services", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(serviceData),
      });

      if (response.ok) {
        // Optionally handle successful submission (e.g., reset form or notify user)
        setCarModel("");
        setPrice("");
        setDescription("");
      } else {
        // Handle error response
        console.error("Failed to add service");
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="add-service-form">
      <div>
        <label htmlFor="carModel">Car Model:</label>
        <input
          type="text"
          id="carModel"
          value={carModel}
          onChange={(e) => setCarModel(e.target.value)}
          required
        />
      </div>
      <div>
        <label htmlFor="price">Price:</label>
        <input
          type="number"
          id="price"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          required
        />
      </div>
      <div>
        <label htmlFor="description">Description:</label>
        <textarea
          id="description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          required
        />
      </div>
      <button type="submit">Add Service</button>
    </form>
  );
};

export default AddServiceForm;