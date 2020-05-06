#include "Suroviny.h"

Suroviny::Suroviny(string nazev) {
	m_nazev = nazev;
}

string Suroviny::getNazev() {
	return m_nazev;
}

void Suroviny::printInfo() {
	cout<<"Nazev suroviny: "<<m_nazev<<endl;
}
