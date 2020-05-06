#ifndef Lod_h
#define Lod_h

#include <iostream>
#include "Inventar.h"
#include "Zbran.h"
#include "Obrana.h"
using namespace std;


class Lod {

private:
	string m_jmeno;
	int m_penize;
	Inventar* m_inventar;
	Zbran* m_zbran;
	Obrana* m_obrana;
	int m_points;

public:
	Lod(string jmeno, Obrana* obrana);
	string getJmeno();
	int getPenize();
	Inventar* getInventar();
	Zbran* getZbran();
	Obrana* getObrana();
	int getPoints();
	void pridejPoints();
	void pridejPenize(int kolik);
	void odeberPenize(int kolik);
	void printInfo();
};

#endif
