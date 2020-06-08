package com.example.app2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Information extends AppCompatActivity {

    RecyclerView recyclerView;
    Button button;
    List<Info> infoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.gov);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.gov.pl/web/koronawirus"));
                startActivity(viewIntent);
            }
        });

        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        ListAdapter movieAdapter = new ListAdapter(infoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);
    }

    private void initData() {
        infoList = new ArrayList<>();
        infoList.add(new Info("OBOWIĄZEK ZASŁANIANIA UST \n W MIEJSCACH PUBLICZNYCH", "do odwołania", "noszenia maseczek lub innego rodzaju zasłaniania ust i nosa.", "Wszyscy mają obowiązek zasłaniania ust i nosa na ulicy, podczas ruchu pieszego, w urzędach, sklepach, miejscach świadczenia usług, zakładach pracy."));
        infoList.add(new Info("KWARANTANNA", "do odwołania", "osób, które:\n" +
                "\n" +
                "- wracają z zagranicy*,\n" +
                "- miały kontakt z osobami zakażonymi (lub potencjalnie zakażonymi) koronawirusem,\n" +
                "- mieszkają z osobą, która zostaje skierowana na kwarantannę", "Kwarantanna trwa 14 dni. Przez ten czas:\n" +
                "\n" +
                "- nie można opuszczać domu,\n" +
                "- spacery z psem, wyjście do sklepu czy do lekarza są zakazane,\n" +
                "- w przypadku, gdy osoba poddana kwarantannie ma bliskie kontakty z innymi osobami w domu – one również muszą zostać poddane kwarantannie."));
        infoList.add(new Info("ZAKAZ ZGROMADZEŃ I IMPREZ", "odwołania", "organizowania zgromadzeń, spotkań, imprez czy zebrań.", "Obowiązuje zakaz wszelkich zgromadzeń oraz imprez.\n" +
                "\n" +
                "Wyłączone są: zakłady pracy.\n" +
                "\n" +
                "Pracodawcy będą musieli jednak zapewnić dodatkowe środki bezpieczeństwa swoim pracownikom. I tak:\n" +
                "\n" +
                "pracownicy mają obowiązek używania rękawiczek lub muszą mieć dostęp do płynów dezynfekujących,\n" +
                "stanowiska pracy poszczególnych osób muszą być oddalone od siebie o co najmniej 1,5 metra."));
        infoList.add(new Info("KOMUNIKACJA PUBLICZNA", "do odwołania", " przejazdów zbiorowym transportem publicznym.", "Zarządzający transportem publicznym mogą podwyższyć limit pasażerów środku transportu publicznego. Ile osób może wejść na pokład?\n" +
                "\n" +
                "Tyle, ile wynosi połowa miejsc siedzących (tak jak dotychczas)\n" +
                "lub\n" +
                "\n" +
                "tyle, ile wynosi 30% liczby wszystkich miejsc siedzących i stojących (w tym przypadku jednak połowa miejsc siedzących musi pozostać wolna)."));
        infoList.add(new Info("UROCZYSTOŚCI RELGIJNE", "do odwołania", "udziału w wydarzeniach o charakterze religijnym.", "Na cmentarzu może przebywać nie więcej niż 50 uczestników podczas jednego pogrzebu, wyłączając z tego osoby sprawujące posługę, a także osoby dokonujące pochowania lub osoby zatrudnione przez zakład lub dom pogrzebowy."));
        infoList.add(new Info("KULTURA", "od 18 maja", "działalności instytucji kultury", "Uchylamy zakaz działalności bibliotek, archiwów, muzeów oraz pozostałej działalności związanej z kulturą.\n" +
                "\n" +
                "Ważne! Instytucje kultury będą otwierane stopniowo i w różnym czasie. O konkretnym terminie decydować będzie organ prowadzący daną placówkę po konsultacji z powiatową stacją sanitarno-epidemiologiczną. \nKina plenerowe – w tym samochodowe – mogą wznowić działalność."));

    }

}
