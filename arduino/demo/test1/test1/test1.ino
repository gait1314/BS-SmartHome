#include "U8glib.h"

#define PIN_LCD_CS			3
#define PIN_LCD_RST			4
#define PIN_LCD_RS			5
#define PIN_LCD_SDA			6
#define PIN_LCD_SCK			7

U8GLIB_MINI12864 u8g(PIN_LCD_SCK, PIN_LCD_SDA, PIN_LCD_CS, PIN_LCD_RS, PIN_LCD_RST);

unsigned char temperature[] U8G_PROGMEM = {0xC0,0x01,0xE0,0x03,0x60,0x1F,0xA0,0x02,0xA0,0x1E,0xA0,0x02,0xA0,0x1E,0xA0,0x02,
0xA0,0x1E,0xB0,0x06,0x98,0x0C,0xCC,0x19,0xEC,0x1B,0xCC,0x19,0x18,0x0C,0xF0,0x07};

unsigned char humidity[] U8G_PROGMEM = {0x00,0x00,0x80,0x00,0xC0,0x01,0xE0,0x03,0xF0,0x07,0xF8,0x0F,0xDC,0x1F,0xFC,0x1F,
0xEE,0x3F,0xE6,0x3F,0xE6,0x3F,0xE6,0x3F,0xCC,0x1F,0xD8,0x0F,0xE0,0x03,0x00,0x00};

unsigned char heat[] U8G_PROGMEM = {0x00,0x00,0x90,0x04,0x48,0x02,0x24,0x01,0x24,0x01,0x48,0x02,0x90,0x04,0x20,0x09,
0x20,0x09,0x90,0x04,0x48,0x02,0x48,0x02,0x90,0x04,0x20,0x09,0x00,0x00,0x00,0x00};

unsigned char pm25[] U8G_PROGMEM = {0x00,0x18,0xDB,0xFF,0x18,0x3C,0x6E,0x00};

unsigned char light[] U8G_PROGMEM = {0x00,0x5A,0x3C,0x66,0x66,0x3C,0x5A,0x00};

unsigned char switchOff[] U8G_PROGMEM = {0x38,0x0E,0x38,0x0E,0x38,0x0E,0x38,0x0E,0xFC,0x3F,0xDE,0x3B,0xBE,0x3D,0x7E,0x3E,
0xBC,0x3D,0xD8,0x1B,0xF0,0x0F,0xE0,0x03,0xC0,0x03,0xE0,0x03,0xC0,0x03,0x00,0x00};

unsigned char switchOn[] U8G_PROGMEM = {0x38,0x0E,0x38,0x0E,0x38,0x0E,0x38,0x0E,0xFC,0x3F,0xFE,0x3F,0xFE,0x3F,0xFE,0x3F,
0xFC,0x3F,0xF8,0x1F,0xF0,0x0F,0xE0,0x03,0xC0,0x03,0xE0,0x03,0xC0,0x03,0x00,0x00};

unsigned char connected[] U8G_PROGMEM = {0x00,0x00,0x08,0x10,0x04,0x20,0x12,0x48,0xA9,0x95,0xD5,0xAB,0xD5,0xAB,0xD5,0xAB,
0xA9,0x95,0x92,0x49,0x84,0x21,0x88,0x11,0x80,0x01,0x80,0x01,0xE0,0x07,0xE0,0x07};

unsigned char noConnection[] U8G_PROGMEM = {0x00,0x00,0x08,0x60,0x04,0x70,0x12,0x78,0xA9,0x9D,0xD5,0xAF,0xD5,0xAF,0xD5,0xAB,
0xF5,0x95,0xF9,0x49,0xBA,0x21,0x9C,0x11,0x8E,0x01,0x87,0x01,0xE3,0x07,0xE0,0x07
};
#include "Tool.h"
void setup() {
//	u8g.begin();
	Serial.begin(19200);
	unsigned char bs[] = {0x79,0xe9,0xf6,0x42};
	float f = Tool::bytesToFloat(bs);
	Serial.print(f);
	while(true);
}

void draw()
{
	/*
	u8g.drawFrame(52,0,76,22);
	u8g.drawFrame(52,21,76,22);
	u8g.drawFrame(52,42,76,21);
	u8g.drawFrame(0,42,53,21);

	u8g.drawXBMP(54,3,16,16,temperature);
	u8g.drawXBMP(54,24,16,16,humidity);
	u8g.drawXBMP(54,45,16,16,heat);
	u8g.drawXBMP(2,44,8,8,pm25);
	u8g.drawXBMP(2,53,8,8,light);

	u8g.setFont(u8g_font_lucasfont_alternate);

	u8g.drawStr(1, 8, "Smar");
	u8g.drawStr(21, 8, "tHome");
	
	u8g.drawStr(71, 10, "I:");
	u8g.drawStr(71, 19, "O:");

	u8g.drawCircle(117, 3, 1);
	u8g.drawStr(120, 10, "C");
	u8g.drawCircle(117, 12, 1);
	u8g.drawStr(120, 19, "C");
	
	u8g.drawStr(71, 31, "I:");
	u8g.drawStr(71, 40, "O:");
	u8g.drawStr(120, 31, "%");
	u8g.drawStr(120, 40, "%");

	u8g.drawStr(71, 52, "I:");
	u8g.drawStr(71, 61, "O:");

	u8g.drawStr(47, 52, "P");
	u8g.drawStr(47, 61, "L");

	//室内外温度
	float f=12.36;  
	u8g.setPrintPos(88,19);
	u8g.print(f, 1);

	char cs[7];  
	dtostrf(f,2,2,cs);
	dtostrf(f,2,2,cs);
	u8g.drawStr(83, 10,cs);

	f = 12.36;
	u8g.drawStr(83, 19,"-");
	dtostrf(f,2,2,cs);
	u8g.drawStr(88, 19, cs);
	u8g.setCursorPos(88,19);
	u8g.print(f, 1);

	//室内外湿度度
	u8g.drawStr(88, 31,"12.3");
	u8g.drawStr(88, 40,"12.3");

	//室内外热度
	u8g.drawStr(88, 52, "12.3");
	u8g.drawStr(88, 61, "12.3");

	//pm2.5
	u8g.drawStr(15, 52, "123.4");
	//光强
	u8g.drawStr(15, 61, "1234");

	u8g.drawXBMP(2,25,16,16,switchOff);
	u8g.drawXBMP(18,25,16,16,switchOff);
	u8g.drawXBMP(34,25,16,16,switchOn);
	u8g.drawXBMP(2,8,16,16,connected);
	u8g.drawXBMP(20,8,16,16,noConnection);*/
}
void loop() {
	u8g.firstPage();
	do {
		draw();
	} while ( u8g.nextPage() );
	
	
	while(true);
}