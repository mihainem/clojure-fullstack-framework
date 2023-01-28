.PHONY: run
run:
	@echo "Running..."
	clj -M:test:dev
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
	clj -M:dev:dev/run:test:cljs:repl:repl/run
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
	clojure -T:build org.corfield.build/uber :lib xo/xo :main xo.core
	@echo "Done."
