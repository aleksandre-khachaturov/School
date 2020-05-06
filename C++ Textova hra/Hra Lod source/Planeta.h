#ifndef Planeta_h
#define Planeta_h

#include <iostream>
#include "Lod.h"
#include "Pirat.h"
using namespace std;

class Planeta {

private:
	string m_nazev;
	vector<Suroviny*> m_suroviny;
	Lod* m_lod;
	Pirat* m_pirat;
	int m_cenaZaProdej;
	int m_cenaZaNakup;

public:
	Planeta(string nazev, int cenaZaProdej, int cenaZaNakup, Pirat* pirat);
	string getNazev();
	Suroviny* getSurovina(int kolikata);
	Lod* getLod();
	Pirat* getPirat();
	int getCenaZaProdej();
	int getCenaZaNakup();
	void setLod(Lod* lod);
	void pridejSurovinu(Suroviny* surovina);
	void odeberSurovinu(int kolikata);
	void boj(Lod* lod);
	void nakupSurovin();
	void prodejSurovin();
	void menu(Lod* lod);
	void printInfo();
};
#endif
