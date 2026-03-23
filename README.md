# 🐶 Dog Register

A simple, reliable system for tracking which dogs belong to whom — because every good dog deserves to be accounted for.

📌 Overview

Dog Register is a lightweight project written in swedish for keeping records of dog ownership. Whether you're managing a small kennel, a neighborhood registry, or just organizing pet data, this tool helps you:

- 🐾 Track dogs and their owners

- 📋 Maintain clear ownership records

- 🔍 Quickly look up who owns which dog

No more confusion at the dog park!

# ✨ Features

- 🐕 Add new dogs to the registry

- 👤 Assign owners to each dog

- 🔎 Search for dogs or owners

- 📂 View all registered dogs and their owners

- 🛠 Simple and extendable structure

# 🚀 Compile and Run

1️⃣ Clone the repository
```bash
 git clone https://github.com/SenecaOS/DogRegister.git
```

2️⃣Navigate to file 
```bash
 cd DogRegister
```
3️⃣ Compile and run the project

```bash
javac *.java
java DogRegister
```
---

# 🧠 How It Works

- Each dog is linked to an owner using a simple relationship model:

- Dog: has a name, breed, age, tail length, and weight

- Owner: has a name and a list of dogs they currently own

- Each dog is assigned to one owner

- This structure allows you to easily manage, search, and update dog ownership records.

---

# 🌱 Future Improvements

- Support for multiple owners per dog

- Graphical interface (desktop or web)

- Database integration for persistent storage

- Authentication and user accounts
