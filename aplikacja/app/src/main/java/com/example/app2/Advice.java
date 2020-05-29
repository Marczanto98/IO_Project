package com.example.app2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Advice extends AppCompatActivity {

    Button virus;
    Button spread;
    Button kids;
    Button antybiotics;
    Button shopping;
    Button protect;
    TextView textView;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.who_advice);
        super.onCreate(savedInstanceState);

        virus = findViewById(R.id.virus);
        spread = findViewById(R.id.spread);
        kids = findViewById(R.id.kids);
        antybiotics = findViewById(R.id.antybiotics);
        shopping = findViewById(R.id.shopping);
        protect = findViewById(R.id.protect);
        textView = findViewById(R.id.info);

        virus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textView.setText("To ostra choroba zakaźna układu oddechowego wywołana zakażeniem wirusem SARS-CoV-2. Jest to wirus RNA " +
                        "osłonięty błoną tłuszczową (lipidową). Dzięki takiej budowie można mu zapobiegać przez zastosowanie środków chemicznych, takich " +
                        "jak zwykłe mydło, alkohol min. 60-70%, preparaty do dezynfekcji i inne wirusobójcze.");
            }
        });

        spread.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textView.setText("Można złapać COVID-19 od innych osób, które mają wirusa. Choroba rozprzestrzenia się głównie między ludźmi"  +
                        " poprzez małe kropelki z nosa lub ust, które są wydalane, gdy osoba z COVID-19 kaszle, kicha lub mówi. Kropelki te są stosunkowo ciężkie, " +
                        "nie podróżują daleko i szybko opadają na ziemię. Osoby mogą złapać COVID-19, jeśli wdychają te krople od osoby zarażonej wirusem. Dlatego ważne jest, aby " +
                        "trzymać się co najmniej 1 metra) z dala od innych. Kropelki te mogą lądować na przedmiotach i powierzchniach wokół osoby, takich jak stoły, klamki i poręcze. "+
                        "Ludzie mogą zarazić się dotykając tych obiektów lub powierzchni, a następnie dotykając oczu, nosa lub ust. Dlatego ważne jest, aby myć ręce regularnie mydłem i wodą lub czyścić je ręcznikiem na bazie alkoholu.\n");
            }
        });

        kids.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textView.setText("Badania wskazują, że dzieci i młodzież również są narażeni na zarażenie jak każda inna grupa wiekowa i mogą rozprzestrzeniać chorobę.\n" +
                        "\n" +
                        "Dotychczasowe dowody sugerują, że dzieci i młodzież są mniej narażeni na ciężką chorobę, ale ciężkie przypadki mogą nadal zdarzyć się w tych grupach wiekowych.\n" +
                        "\n" +
                        "Dzieci i dorośli powinni postępować zgodnie z tymi samymi wskazówkami dotyczącymi samo kwarantanny i izolacji, jeśli istnieje ryzyko, że zostali narażeni lub wykazują objawy." +
                        " Szczególnie ważne jest, aby dzieci unikały kontaktu z osobami starszymi i innymi, zagrożonymi cięższą chorobą.");
            }
        });

        antybiotics.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textView.setText("Podczas gdy niektóre zachodnie, tradycyjne lub domowe środki zaradcze mogą zapewnić komfort i złagodzić objawy łagodnego COVID-19, " +
                        "nie ma leków, które zapobiegałyby lub leczyły chorobę. WHO nie zaleca samoleczenia jakimikolwiek lekami, w tym antybiotykami, w celu zapobiegania "+
                        "lub leczenia COVID-19. Trwa jednak kilka prób klinicznych zarówno leków zachodnich, jak i tradycyjnych. WHO koordynuje wysiłki na rzecz opracowania "+
                        "szczepionek i leków w celu zapobiegania i leczenia COVID-19 i będzie nadal dostarczać aktualne informacje, gdy tylko wyniki badań będą dostępne.\n");
            }
        });

        shopping.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                textView.setText("Podczas zakupów spożywczych zachowaj co najmniej 1 metr odległości od innych i unikaj dotykania oczu, "+
                        "ust i nosa. Jeśli to możliwe, zdezynfekuj uchwyty wózków sklepowych lub koszy przed zakupami. Po powrocie do domu dokładnie "+
                        "umyj ręce, a także po pracy i przechowywaniu zakupionych produktów.\n" +
                        "\n" +
                        "Obecnie nie ma potwierdzonego przypadku COVID-19 przenoszonego przez żywność lub opakowania żywności.");
            }
        });

        protect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Aby zapobiec rozprzestrzenianiu się COVID-19:\n" + "\n" +
                        "- Często myj ręce. Używaj wody z mydłem i środka dezynfekującego na bazie alkoholu.\n" + "\n" +
                        "- Zachowaj bezpieczną odległość od osób kaszlących i kichających.\n" + "\n" +
                        "- Nie dotykaj oczu, nosa ani ust.\n" + "\n" +
                        "- Gdy kaszlesz lub kichasz, zakrywaj nos i usta wewnętrzną stroną łokcia lub chusteczką.\n" + "\n" +
                        "- Jeśli źle się czujesz, zostań w domu.\n" + "\n" +
                        "- Jeśli masz gorączkę, kaszel i trudności z oddychaniem, skontaktuj się z lekarzem. Pamiętaj, by wcześniej zadzwonić i umówić się na wizytę.\n" + "\n" +
                        "Postępuj zgodnie z instrukcjami lokalnego urzędu ds. ochrony zdrowia.\n" +
                        "Unikanie niepotrzebnych wizyt w placówkach medycznych pozwala systemom opieki zdrowotnej na bardziej efektywne działanie, a tym samym ochronę Ciebie i innych.");
            }
        });
    }
}
