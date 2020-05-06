#ifndef Suroviny_h
#define Suroviny_h

#include <iostream>
using namespace std;

class Suroviny {

private:
	string m_nazev;

public:
	Suroviny(string nazev);
	string getNazev();
	void printInfo();
};
#endif
