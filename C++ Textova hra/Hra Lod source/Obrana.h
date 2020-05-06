#ifndef Obrana_h
#define Obrana_h

#include <iostream>
using namespace std;

class Obrana {

protected:
	int m_zivot;

public:
	Obrana(int zivot);
	virtual int getZivot() = 0;
	virtual void pridejZivot() = 0;
	virtual void odeberZivot(int kolik) = 0;
	virtual void printInfo() = 0;
};

#endif
