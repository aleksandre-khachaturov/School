#include "Pirat.h"

Pirat::Pirat(Zbran* zbran, Obrana* obrana, int bonus) {
	m_zbran = zbran;
	m_obrana = obrana;
	m_bonus = bonus;
}

Zbran* Pirat::getZbran() {
	return m_zbran;
}

Obrana* Pirat::getObrana() {
	return m_obrana;
}

int Pirat::getBonus() {
	return m_bonus;
}

void Pirat::printInfo() {
	cout<<endl;getZbran()->printInfo(); cout<<endl;
	cout<<endl;getObrana()->printInfo(); cout<<endl;
	cout<<endl<<"Bonus"<<getBonus()<<endl;
}
