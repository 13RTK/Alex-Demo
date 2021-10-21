import cv2 as cv

def face_detect_demo():
    gray = cv.cvtColor(img, cv.COLOR_BGR2GRAY)
    face_detect = cv.CascadeClassifier('/Users/alex/tensorflow_macos/arm64/opencv-4.5.0/data/haarcascades/haarcascade_frontalface_default.xml')
    face = face_detect.detectMultiScale(gray)
    for x, y, w, h in face:
        cv.rectangle(img, (x, y), (x + w, y + h), color = (0, 0, 255), thickness = 2)
    cv.imshow('result', img)

img = cv.imread('Test.jpg')
face_detect_demo()

while True:
    if ord('q') == cv.waitKey(0):
        break
cv.destroyAllWindows