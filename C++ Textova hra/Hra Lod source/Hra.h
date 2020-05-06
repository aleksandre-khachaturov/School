#ifndef Hra_h
#define Hra_h

#include <iostream>
#include <vector>
#include "Lod.h"
#include "Mapa.h"
using namespace std;

class Hra {

private:
	vector<Lod*> m_hrac;
	vector<Mapa*> m_mapa;

public:
	Hra();
	void menu();
	void novaHra();
	void ukazPointy();
	void pridejHrace(Lod* lod);
	Lod* getHrace(int ktery);
	Mapa* getMapa(int ktera);
	int getPocetHracu();
	~Hra();
};


#endif
