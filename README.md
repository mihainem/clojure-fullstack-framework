# Clojure Fullstack Framework

Welcome to the Clojure Fullstack Framework! This framework is designed to provide a progressive and modular development experience for building full-stack applications using Clojure. The repository contains several branches, each representing a different stage in the development of the framework.

## Branches

### 1. `clj-base`

The `clj-base` branch serves as the foundation for the Clojure Fullstack Framework. It includes the basic structure for an API written in Clojure, with the following namespaces:

- **Jetty:** Provides a lightweight web server for serving the API.
- **Reitit:** A data-driven router for handling API routes.
- **Integrant:** A configuration and component system for managing the application's lifecycle.
- **Aero:** A configuration library for managing settings in a concise manner.
- **Environ:** A library for managing environment-specific configurations.
- **Taoesso-timbre:** Timbre logging configuration for improved logging.

This branch sets up the essential components to kickstart your full-stack application development.

### 2. `clj+postgres`

The `clj+postgres` branch builds upon the `clj-base` foundation and introduces database handling. Key additions include:

- **Postgres:** Integration with the Postgres database.
- **Ragtime:** A migration library for managing database schema changes.

This branch allows you to start developing a Rest API with persistent data storage.

### 3. `clj+psql+cljs-base`

The `clj+psql+cljs-base` branch extends the framework to support full-stack development by introducing ClojureScript and Shadow CLJS. This includes:

- **Shadow CLJS:** A ClojureScript compiler for efficient compilation and hot code reloading.
- Additional configurations for the UI in the `src/ui` directory.

This branch completes the full-stack framework, enabling you to build both the API and the user interface with Clojure.

## Directory Structure

- `src/api`: Contains the Clojure source code for the API.
- `src/ui`: Houses the ClojureScript code for the user interface.

## Getting Started

To get started with a specific branch, clone the repository and switch to the desired branch:

```bash
git clone https://github.com/mihainem/clojure-fullstack-framework.git
cd clojure-fullstack-framework
git checkout clj-base  # Replace with the desired branch name
