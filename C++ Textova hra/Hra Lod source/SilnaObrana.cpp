#include "SilnaObrana.h"

SilnaObrana::SilnaObrana(int zivot, int bonusZivota):Obrana(zivot) {
	m_zivot = zivot + bonusZivota;
	m_bonusZivota = bonusZivota;
}

int SilnaObrana::getZivot() {
	return m_zivot;
}

void SilnaObrana::pridejZivot() {
	m_zivot += 5;
}

void SilnaObrana::odeberZivot(int kolik) {
	m_zivot -= kolik;
}

void SilnaObrana::printInfo() {
	cout<<"Zivot: "<<m_zivot<<endl;
}
