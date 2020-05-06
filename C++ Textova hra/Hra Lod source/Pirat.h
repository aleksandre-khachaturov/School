#ifndef Pirat_h
#define Pirat_h

#include <iostream>
#include "Zbran.h"
#include "Obrana.h"
#include "SilnaObrana.h"
#include "SlabaObrana.h"

using namespace std;

class Pirat {

private:
	Zbran* m_zbran;
	Obrana* m_obrana;
	int m_bonus;

public:
	Pirat(Zbran* zbran, Obrana* obrana, int bonus);
	Zbran* getZbran();
	Obrana* getObrana();
	int getBonus();
	void printInfo();
};
#endif
