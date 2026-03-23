🐶 Dog Register

A simple, reliable system for tracking which dogs belong to whom — because every good dog deserves to be accounted for.

📌 Overview

This Dog Register project is a lightweight project designed to keep records of dog ownership. Whether you're managing a small kennel, a neighborhood registry, or just organizing pet data, this tool helps you:

Track dogs and their owners
Maintain clear ownership records
Quickly look up who owns which dog

No more confusion at the dog park.

✨ Features
🐕 Add new dogs to the registry
👤 Assign owners to each dog
🔍 Search for dogs or owners
📋 View all registered dogs and their owners
🛠 Simple and extendable structure
🚀 Getting Started

1. Clone the repository
   
git clone https://github.com/SenecaOS/DogRegister

2. Run the project
   
cd DogRegister
javac src/*.java
# Run the main class
java -cp src DogRegister

🧠 How It Works

Each dog is linked to an owner using a simple relationship model:

A Dog has a name, breed, age, tail length and weight
An Owner has a name and a list associated with the dogs in its current ownership
Each dog is assigned to one owner
