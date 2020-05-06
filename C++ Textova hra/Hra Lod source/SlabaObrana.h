#ifndef SlabaObrana_h
#define SlabaObrana_h

#include <iostream>
#include "Obrana.h"
using namespace std;

class SlabaObrana : public Obrana {

public:
	int m_bonusZivota;
	SlabaObrana(int zivot, int bonusZivota);
	int getZivot();
	void pridejZivot();
	void odeberZivot(int kolik);
	void printInfo();
};
#endif
