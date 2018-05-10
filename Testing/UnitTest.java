import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DbHelperTest {
@Test
void testChange_SoundEffects_Power() {
db = this.getWritableDatabase();
String count = "SELECT COUNT(*) FROM " + TABLE_SETTINGS;
Cursor cur = db.rawQuery(count, null);
cur.moveToFirst();
assertEquals(cur.moveToFirst() + 1, cur.getQuestion());
assertEquals(cur.moveToFirst() + 2 , cur.getQuestion());
assertEquals(cur.moveToFirst() + 3, cur.getQuestion());
assertEquals(cur.moveToFirst() + 4, cur.getQuestion());
}
@Test
void testGrabQuestion_withID() {
Cursor cur = db.query(TABLE_QUESTIONS, columns, "ID = " + 'M18');
assertEquals('Quesition Multiply', cur.getQuestion());
}
@Test
void testGrabQuestion_withDifficulty() {
Cursor cur = db.query(TABLE_QUESTIONS, columns, COL11 + " = \"" + medium+
"\"", null, null, null, null, null);
assertEquals('Question Range : Medium' cur.getQuestion());
}

class LevelMenuTest {
@Test
final void testButtonOnClickEasy() {
startActivity(intent);
Intent intent = new Intent(this, level_menu.easy);
assertEquals(getIntent().getExtras(), intent.getExtras());
}
@Test
final void testButtonOnClickMedium() {
startActivity(intent);
Intent intent = new Intent(this, level_menu.medium);
assertEquals(getIntent().getExtras(), intent.getExtras());
}
@Test
final void testButtonOnClickHard() {
startActivity(intent);
Intent intent = new Intent(this, level_menu.hard);
assertEquals(getIntent().getExtras(), intent.getExtras());
}
@Test
final void testOnResume() {
AudioManager manager =
(AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
assertEquals(mySong.start(), manager.playing());
}
