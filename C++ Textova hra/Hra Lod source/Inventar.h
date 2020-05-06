#ifndef Inventar_h
#define Inventar_h

#include <iostream>
#include <vector>
#include "Suroviny.h"
using namespace std;


class Inventar {

private:
	int m_velikost;
	vector<Suroviny*> m_suroviny;

public:
	Inventar();
	int getVelikost();
	Suroviny* getSurovina(int kolikata);
	int getPocetSurovin();
	void pridejSurovinu(Suroviny* surovina);
	void odeberSurovinu(int kolikata);
	void printInfo();
};
#endif // Inventar_h
