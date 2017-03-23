package com.hyperiongray.sitehound.backend.service.htmlunit;

import com.hyperiongray.sitehound.backend.service.nlp.WordCounter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tomas on 5/27/15.
 */
public class WordCounterTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(WordCounterTest.class);
	private WordCounter instance = new WordCounter();

	// TODO toObject this from a file
	private static final List<String> STOP_WORDS =  Arrays.asList(new String[]{"of", "i", "am", "a", "is"});


	@Test
	public void testWordCounter(){
		String content = "the brown fox jumped over the red fence. The blue fox jumped over the blue blue carpet";
		List<String> countWords = instance.getMostFrequentWords(content, STOP_WORDS, 10);
		LOGGER.info("countWords", countWords);
	}

	@Test
	public void wordCounterRussianTest(){
		String content =
				                 "Lib.Ru: Библиотека Максима МошковаПри поддержке Федерального агентства по печати и массовым коммуникациям.Поиск: Проза Переводы Поэзия Фантастика Детективы Классика История  И ДР.>>>[НОВИНКИ][Хитпарад][Самиздат][Музыка][Художники][Заграница][Туризм][ArtOfWar][Окопка][Форум]Авторские разделы: Современная Фантастика Остросюжетная Фотохостинг [Зеркала] [koi-win-lat]  \n" +
				                 "23 aug 06. 5.5Gb. Самая известная в Рунете www-библиотека, открыта в 1994. Авторы и читатели ежедневно пополняют ее. Художественная литература, фантастика и политика, техдокументация и юмор, история и поэзия, КСП и русский рок, туризм и парашютизм, философия и эзотерика, и т.д. и т.п.\n" +
				                 " Награды:НИП-2003,POTOP, IT-100,РИФ-2001. \n" +
				                 " ЗЕРКАЛА: lib.ru, зеркало на kulichki.com, tomsk.ru Украина (UA-IX) himoza.org\n" +
				                 "Новинки Lib.ru\n" +
						                 "http://t " +
						                 "http://t " +
						                 "http://t " +
						                 "http://t " +
						                 "http://t " +
						                 "http://t " +
						                 "http://t " +
						                 "http://t " +
						                 "#aaa " +
						                 "#aaa " +
						                 "#aaa " +
						                 "#aaa " +
						                 "#aaa " +
						                 "#aaa " +
						                 "#aaa " +
						                 "#aaa " +
						                 "#aaa " +
						                 "#aaa " +
						                 "\"abb " +
						                 "\"abb " +
						                 "\"abb " +
						                 "\"abb " +
						                 "\"abb " +
						                 "\"abb " +
						                 "\"abb " +
						                 "\"abb " +
						                 "\"abb " +
						                 "\"abb " +

						                 "\"abc. " +
						                 "\"abc. " +
						                 "\"abc. " +
						                 "\"abc. " +
						                 "\"abc. " +
						                 "\"abc. " +
						                 "\"abc. " +
						                 "\"abc. " +
						                 "\"abc. " +
						                 "\"abc. " +

						                 "\"abd: " +
						                 "\"abd: " +
						                 "\"abd: " +
						                 "\"abd: " +
						                 "\"abd: " +
						                 "\"abd: " +
						                 "\"abd: " +
						                 "\"abd: " +
						                 "\"abd: " +
						                 "\"abd: " +
						                 "\"abd: " +
						                 "\"abd: " +
						                 "\"abd: " +

				                 "О.Меркулов. На двух берегах (Военная проза)\n" +
				                 " Глеб Бобров. Эпоха Мертворожденных (Украинская фантастика)\n" +
				                 " Владимир Тен. Pretty Woman. Вид сбоку (Проза)\n" +
				                 " Е.Кисин. Памяти Тихона Хренникова (Воспоминания)\n" +
				                 " А.Козлачков. Запах искусственной свежести (Премия Белкина)\n" +
				                 " Н.Иванов. Без права на славу (Проза)\n" +
				                 " В.Дворцов. Тогда, когда случится (Проза)\n" +
				                 " В.Вознесенский. Евангелие рукотворных богов (Постапокалипсис)\n" +
				                 " И.Фролов. Бортжурнал No 57-33-10 (Окончание)\n" +
				                 " Р.Мейсон. Цыпленок и ястреб (Вьетнамская война)\n" +
				                 " Сбор средств на открытие мемориальной доски Михаилу Леонидовичу Анчарову\n" +
				                 "\"RecSelf\" - служба пользовательских торговых объявлений \"куплю/продам\"\n" +
				                 "(  0k)       НОВЫЕ ПОСТУПЛЕНИЯ В БИБЛИОТЕКУ (149 )       РУССКАЯ СОВРЕМЕННАЯ ПРОЗА (163 ) Мемуары Чеченской войны (204 ) 29 Jul РУССКАЯ И ЗАРУБЕЖНАЯ ПОЭЗИЯ (192 )       ЗАРУБЕЖНАЯ ПРОЗА ( 56 )       ЗАРУБЕЖНЫЙ ДЕТЕКТИВ (238 )       СОВЕТСКАЯ ФАНТАСТИКА (190 )       ЗАРУБЕЖНАЯ ФАНТАСТИКА (296 )       Авторская песня и русский рок (174 )       Альпинизм и горный туризм (198 )       Парашютизм (797 ) Библиотека изобразительных искусств (9897) Литературный журнал \"Самиздат\" (8467) MP3: \"Музыкальный хостинг\" (847 ) Зарубежные впечатления \"Заграница\" \n" +
				                 " (266 )       Впечатления о заграничной жизни (104 )       Учим английский язык ( 56 )       Кинофильмы, TV, video... (110 )       Зарубежная рок-музыка (248 )       Юмор \n" +
				                 "\n" +
				                 "ПРОЗА, ПОЭЗИЯ\n" +
				                 "\n" +
				                 " (204 ) 29 Jul РУССКАЯ И ЗАРУБЕЖНАЯ ПОЭЗИЯ (149 )       РУССКАЯ СОВРЕМЕННАЯ ПРОЗА ( 64 )       РУССКАЯ ДОВОЕННАЯ ЛИТЕРАТУРА (212 )       РУССКАЯ КЛАССИКА (  5 )       ЛИТЕРАТУРА БЛИЖНЕГО ЗАРУБЕЖЬЯ ( 73 )  1 Sep СОВРЕМЕННАЯ ДРАМАТУРГИЯ (231 )       ПРОЗА 90-х - 2000-х годов (192 )       ПЕРЕВОДНАЯ ПРОЗА \n" +
				                 "СТАРИННАЯ ЛИТЕРАТУРА\n" +
				                 "( 60 )       СТАРИННАЯ ЕВРОПЕЙСКАЯ ЛИТЕРАТУРА ( 75 )       АНТИЧНАЯ ЛИТЕРАТУРА ( 34 )       КИТАЙСКАЯ ПОЭЗИЯ \n" +
				                 "ДЕТСКАЯ И ПРИКЛЮЧЕНЧЕСКАЯ\n" +
				                 "(144 )       СКАЗКИ (102 )       ПРИКЛЮЧЕНИЯ \n" +
				                 "";
		List<String> countWords = instance.getMostFrequentWords(content, STOP_WORDS, 10);
		LOGGER.info("countWords: ", countWords);
		System.out.println(countWords);
	}

}
