CXXFLAGS =	-O2 -g -Wall -fmessage-length=0

OBJS =		test4cpp.o

LIBS =

TARGET =	test4cpp.exe

$(TARGET):	$(OBJS)
	$(CXX) -o $(TARGET) $(OBJS) $(LIBS)

all:	$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)
