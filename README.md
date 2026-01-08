# 🧠 NewsMind  
**AI 기반 개인화 글로벌 뉴스 큐레이션 서비스**

NewsMind는 사용자의 **관심 국가, 카테고리, 키워드**를 기반으로  
글로벌 뉴스를 수집하고, **자동 번역 + AI 분석**을 통해  
한눈에 이해할 수 있는 맞춤형 뉴스 피드를 제공하는 서비스입니다.

### 🎬 NewsMind 서비스 시연 영상
https://github.com/user-attachments/assets/5241028c-869a-4262-a3c6-44a6940cb79a

---

## ✨ 주요 기능

- 🔍 **맞춤 뉴스 피드**
  - 관심 국가 / 카테고리 / 키워드 기반 뉴스 자동 추천
- 🌍 **글로벌 뉴스 수집**
  - 해외 뉴스 API 연동으로 다양한 국가의 기사 제공
- 🌐 **자동 번역**
  - Microsoft Azure Translator를 활용한 실시간 기사 번역
- 🤖 **AI 뉴스 분석**
  - 기사 요약
  - 핵심 키워드 추출
  - 기사 내용 기반 인사이트 분석
- 🔐 **개인화 서비스**
  - 카카오 간편 로그인을 통한 사용자 맞춤 뉴스 관리

---

## 🛠 기술 스택

### 📡 External APIs
- **News API** : `NewsData.io`
- **Translation API** : `Microsoft Azure Translator`
- **Login API** : `Kakao OAuth`
- **AI Model** : `OpenAI GPT-4o-mini`

### 🖥 Backend & Infrastructure
- **Server** : `AWS EC2`
- **Database** : `MySQL (newsmind)`
- **Architecture** : RESTful API 기반 서버 구조

---

## 🗂 데이터 흐름 요약

1. 사용자가 관심 국가 / 카테고리 / 키워드 설정
2. NewsData.io를 통해 관련 뉴스 수집
3. Azure Translator로 기사 번역
4. OpenAI API를 통한 요약 · 키워드 · 분석 생성
5. 개인화된 뉴스 피드로 사용자에게 제공

<img width="500" height="500" alt="newsmind" src="https://github.com/user-attachments/assets/b1d70d4b-46da-4858-8e6a-3eaf42232e02" />

---

## 🎯 서비스 목표

> “정보의 양이 아니라 **이해의 깊이**를 제공하는 뉴스”

- 글로벌 뉴스 접근 장벽 제거
- 사용자의 관심사에 최적화된 정보 제공
- AI를 활용한 빠르고 정확한 뉴스 인사이트 전달

---
