import java.util.ArrayList;
import java.util.Scanner;

public class ClockAlgorihtm {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		int boyut = 5;

		System.out.printf("Ýsim Girin (Çýkýþ için Ç giriniz) : ");
		final ArrayList<String> arrayList = new ArrayList<>();
		while (true) {
			final String isimler = input.next();
			if ("Ç".equals(isimler) || "ç".equals(isimler)) {
				break;
			}
			arrayList.add(isimler);
		}
		final String[] array = new String[arrayList.size()];
		arrayList.toArray(array);

		final Frame frames[] = new Frame[boyut];
		for (int i = 0; i < boyut; ++i) {
			frames[i] = new Frame();
		}

		Clock(frames, boyut, array);

	}

	private static int Clock(final Frame frames[], final int numberOfFrames, final String... arrayIsimler) {
		int sira = 0;
		int aa = 0;

		System.out.println("            ---- Durum: 0 ----");

		for (int i = 0; i < numberOfFrames; ++i) {
			final Frame frame = frames[i];
			System.out.print("Stack :" + frame.reference + " " + frame.deger);
			System.out.printf(" %s:      ", (i == sira) ? "    (sýra)" : "");
			System.out.println();
		}
		System.out.println();

		for (int n = 0; n < arrayIsimler.length; ++n) {
			System.out.println("           ---- Durum: " + (n + 1) + " ----");
			final String referans = arrayIsimler[n];
			if (referans == null || referans.equalsIgnoreCase("null")) {
				System.out.printf(" Biþey bulunamadi... ");
				continue;
			}
			System.out.println("Girilen isim ---> " + referans + "\n");

			boolean fault = true;
			for (int i = 0; i < numberOfFrames; ++i) {
				final Frame frame = frames[i];
				if (referans.equals(frame.reference)) {

					frame.deger = 1;
					fault = false;
					break;
				}
			}

			if (fault) {

				while (frames[sira].deger == 1) {
					frames[sira].deger = 0;
					sira = (sira + 1) % numberOfFrames;
				}

				frames[sira].reference = referans;
				frames[sira].deger = 1;

				sira = (sira + 1) % numberOfFrames;

				++aa;
			}
			for (int i = 0; i < numberOfFrames; ++i) {
				final Frame frame = frames[i];
				System.out.print("Stack :" + frame.reference + " " + frame.deger);
				System.out.printf(" %s:      ", (i == sira) ? "    (sýra)" : "");
				System.out.println();
			}
			System.out.println();
		}
		return aa;
	}

}

class Frame {

	String reference;
	byte deger;

	Frame() {
		reference = null;
		deger = 0;
	}
}