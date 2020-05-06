#include "Zbran.h"

Zbran::Zbran(int sila) {
	m_sila = sila;
}

int Zbran::getSila() {
	return m_sila;
}

void Zbran::vylepsitZbran() {
	m_sila += 7;
}

void Zbran::printInfo() {
	cout<<"Sila: "<<m_sila<<endl;
}
