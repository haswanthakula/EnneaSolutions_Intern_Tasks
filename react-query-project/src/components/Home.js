import React, { useState, useEffect } from 'react';
import './Home.css';

const carouselSlides = [
  {
    title: "Tanstack Query Page",
    description: "Explore Rick and Morty Characters using React Query's powerful data fetching capabilities",
    features: [
      "Fetches characters from Rick and Morty API",
      "Automatic caching and background updates",
      "Responsive grid layout",
      "Detailed character information"
    ],
    image: "/images/tanstack-preview.png"
  },
  {
    title: "Traditional Fetch Page",
    description: "View Memes using traditional JavaScript fetch method",
    features: [
      "Displays memes from Imgflip API",
      "Manual state management",
      "Demonstrates classic data fetching approach",
      "Simple and straightforward implementation"
    ],
    image: "/images/traditional-preview.png"
  },
  {
    title: "Mutation Page",
    description: "Interactive post creation and deletion using React Query Mutations",
    features: [
      "Add and delete posts dynamically",
      "Real-time updates with JSON Server",
      "Demonstrates mutation capabilities",
      "Instant query invalidation and refetching"
    ],
    image: "/images/mutation-preview.png"
  },
  {
    title: "React Query Benefits",
    description: "Why React Query is a game-changer for data management",
    features: [
      "Automatic caching",
      "Background updates",
      "Simplified error handling",
      "Reduced boilerplate code",
      "Improved performance"
    ],
    image: "/images/react-query-benefits.png"
  },
  {
    title: "Project Overview",
    description: "A comprehensive demonstration of modern React data fetching techniques",
    features: [
      "Multiple data fetching strategies",
      "Real-world API integrations",
      "Responsive design",
      "Interactive user experiences"
    ],
    image: "/images/project-overview.png"
  }
];

const Home = () => {
  const [currentSlide, setCurrentSlide] = useState(0);

  useEffect(() => {
    const slideInterval = setInterval(() => {
      setCurrentSlide((prev) => (prev + 1) % carouselSlides.length);
    }, 5000);

    return () => clearInterval(slideInterval);
  }, []);

  const handleDotClick = (index) => {
    setCurrentSlide(index);
  };

  return (
    <div className="home-container-full">
      <h1 className="page-title">React Query Project</h1>
      <div className="carousel-container-full">
        {carouselSlides.map((slide, index) => (
          <div 
            key={index} 
            className={`carousel-slide-full ${index === currentSlide ? 'active' : ''}`}
          >
            <div className="slide-content-full">
              <div className="slide-overlay"></div>
              <img 
                src={slide.image} 
                alt={slide.title} 
                className="slide-background"
                onError={(e) => {
                  e.target.src = 'https://via.placeholder.com/1920x1080?text=Image+Not+Found';
                }}
              />
              <div className="slide-text-full">
                <h2>{slide.title}</h2>
                <p>{slide.description}</p>
                <ul>
                  {slide.features.map((feature, featureIndex) => (
                    <li key={featureIndex}>{feature}</li>
                  ))}
                </ul>
              </div>
            </div>
          </div>
        ))}
        
        <div className="carousel-dots-full">
          {carouselSlides.map((_, index) => (
            <span 
              key={index} 
              className={`dot-full ${index === currentSlide ? 'active' : ''}`}
              onClick={() => handleDotClick(index)}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default Home;
