#ifndef Mapa_h
#define Mapa_h

#include <iostream>
#include <vector>
#include "Planeta.h"
#include "Suroviny.h"
using namespace std;

class Mapa {

private:
	vector<Planeta*> m_planety;
	vector<Suroviny*> m_suroviny;

public:
	Mapa();
	void menu(Lod* lod);
	Planeta* getPlaneta(int ktera);
    int p;
};

#endif
