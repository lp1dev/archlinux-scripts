CXXFLAGS = -static -static-libgcc -static-libstdc++ -Wall -Wextra -Werror -O3 -std=c++11
LDFLAGS = -lcryptopp 
     
PROGRAM = dpg
SOURCE  = main.cpp
     
all: $(PROGRAM)
     
$(PROGRAM): $(SOURCE) ; $(CXX) $(CXXFLAGS) -o $(PROGRAM) $(SOURCE) $(LDFLAGS)
     
clean: ; rm -f $(PROGRAM)

