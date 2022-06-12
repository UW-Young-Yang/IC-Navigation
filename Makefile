run: compile

compile:Navigation.class
	@java Navigation

test:TestNavigation.class
	@java -jar junit5.jar --class-path . --scan-classpath --details tree
	
TestNavigation.class:
	@javac -cp .:junit5.jar TestNavigation.java
	
Navigation.class:
	@javac Navigation.java
clean:
	$(RM) *.class
