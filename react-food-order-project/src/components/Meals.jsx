import { useState, useEffect } from "react";
import MealItem from "./MealItem.jsx";

const Meals = () => {
  const [loadedMeals, setLoadedMeals] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchMeals() {
      try {
        const response = await fetch("http://localhost:4000/meals");

        if (!response.ok) {
          throw new Error("Failed to fetch meals!");
        }

        const meals = await response.json();
        setLoadedMeals(meals);
      } catch (err) {
        setError(err.message);
      } finally {
        setIsLoading(false);
      }
    }

    fetchMeals();
  }, []);

  if (isLoading) {
    return <p className="meals-loading">Loading meals...</p>;
  }

  if (error) {
    return <p className="meals-error">{error}</p>;
  }

  return (
    <ul id="meals">
      {loadedMeals.map((meal) => (
        <MealItem key={meal.id} meal={meal} />
      ))}
    </ul>
  );
};

export default Meals;
