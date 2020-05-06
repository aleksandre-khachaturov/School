#include "SlabaObrana.h"

SlabaObrana::SlabaObrana(int zivot, int bonusZivota):Obrana(zivot) {
	m_zivot = zivot + bonusZivota - 50;
	m_bonusZivota = bonusZivota;
}

int SlabaObrana::getZivot() {
	return m_zivot-20;
}

void SlabaObrana::pridejZivot() {
    m_zivot += 2;
}

void SlabaObrana::odeberZivot(int kolik) {
	m_zivot -= kolik-5;
}

void SlabaObrana::printInfo() {
	cout<<"Zivot: "<<m_zivot<<endl;
}
