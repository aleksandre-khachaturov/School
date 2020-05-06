#ifndef SilnaObrana_h
#define SilnaObrana_h

#include <iostream>
#include "Obrana.h"
using namespace std;

class SilnaObrana : public Obrana {

public:
	int m_bonusZivota;
	SilnaObrana(int zivot, int bonusZivota);
	int getZivot();
	void pridejZivot();
	void odeberZivot(int kolik);
	void printInfo();
};
#endif
