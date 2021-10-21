import cv2 as cv

def face_detect_demo(img):
    gray = cv.cvtColor(img, cv.COLOR_BGR2GRAY)
    face_detect = cv.CascadeClassifier('/Users/alex/tensorflow_macos/arm64/opencv-4.5.0/data/haarcascades/haarcascade_frontalface_default.xml')
    face = face_detect.detectMultiScale(gray)
    for x, y, w, h in face:
        cv.rectangle(img, (x, y), (x + w, y + h), color = (0, 0, 255), thickness = 2)
    cv.imshow('result', img)

cap = cv.VideoCapture('psycho.mp4')


while True:
    flag, frame = cap.read()
    if not flag:
        break
    face_detect_demo(frame)
    if ord('q') == cv.waitKey(10):
        break
cv.destroyAllWindows
cap.release()