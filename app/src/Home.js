import React from "react";
import AppNav from "./AppNav";

function Home() {
  return (
    <div>
      <AppNav />
      <h2
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          height: "100vh"
        }}
     >
        Welcome to easy expense app!
      </h2>
    </div>
  );
}

export default Home;
