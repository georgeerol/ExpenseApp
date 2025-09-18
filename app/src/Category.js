import React, { useEffect, useState } from "react";
import AppNav from "./AppNav";

function Category() {
  const [isLoading, setIsLoading] = useState(true);
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    let isMounted = true;
    (async () => {
      const response = await fetch("/api/categories");
      const body = await response.json();
      if (isMounted) {
        setCategories(body);
        setIsLoading(false);
      }
    })();
    return () => {
      isMounted = false;
    };
  }, []);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <AppNav />
      <h2>Categories</h2>
      {categories.map((category) => (
        <div key={category.id}>{category.name}</div>
      ))}
    </div>
  );
}

export default Category;
