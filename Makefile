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
