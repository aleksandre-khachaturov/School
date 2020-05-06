#ifndef Zbran_h
#define Zbran_h

#include <iostream>
using namespace std;

class Zbran {

private:
	int m_sila;

public:
	Zbran(int sila);
	int getSila();
	void vylepsitZbran();
	void printInfo();
};
#endif
