.PHONY: run
run:
	@echo "Running..."
	clj -M:test:dev -m api.core
	@echo "Done."

.PHONY: test
test:
	@echo "Testing..."
	clj -M:test
	@echo "Done."

.PHONY: clean
clean:
	@echo "Cleaning..."
	rm -rf node_modules
	@echo "Done."

.PHONY: repl
repl:
	@echo "Running..."
	clj -M:dev:test:repl:repl/run  -m api.core
	@echo "Done."


.PHONY: lint
lint:
	clojure -M:lint/clj-kondo --lint src test

.PHONY: style-check
style-check:
	which cljstyle &> /dev/null && cljstyle check || cljstyle check

.PHONY: style-fix
style-fix:
	which cljstyle &> /dev/null && cljstyle fix || cljstyle fix

.PHONY: build
build:
	@echo "Building..."
	@echo "# run the tests using default options:"
	clojure -T:build org.corfield.build/run-tests
	
	@echo "# clean the target folder:"
	clojure -T:build org.corfield.build/clean

	@echo "# build the project:"
	clojure -T:build org.corfield.build/uber :lib api/api :main api.core
	@echo "Done."

.PHONY: docker
docker:
	@echo "Building docker db image..."
	direnv exec . docker-compose up -d --build 
	@echo "Done."

.PHONY: docker-down
docker-down:
	@echo "Stopping and removing docker db container, image, volume"
	docker-compose down -v --rmi all --remove-orphans
	@echo "Done."

.PHONY: sync-ignore
sync-ignore:
	@echo "Syncronizing .gitignore and .dockerignore files"
	sort .gitignore .dockerignore | uniq > output.txt
	cp output.txt .dockerignore
	mv output.txt .gitignore
